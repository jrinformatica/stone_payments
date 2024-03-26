import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:stone_payments/enums/status_transaction_enum.dart';
import 'package:stone_payments/enums/type_owner_print_enum.dart';

import 'enums/type_transaction_enum.dart';
import 'models/item_print_model.dart';
import 'stone_payments_platform_interface.dart';

/// An implementation of [StonePaymentsPlatform] that uses method channels.
class MethodChannelStonePayments extends StonePaymentsPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('stone_payments');

  final _paymentController = StreamController<StatusTransaction>.broadcast();
  final _qrcodeController = StreamController<String>.broadcast();
  final _transactionController = StreamController<String>.broadcast();

  @override
  Stream<StatusTransaction> get onMessage => _paymentController.stream;

  @override
  Stream<String> get onTransaction => _transactionController.stream;

  @override
  Stream<String> get onQRCode => _qrcodeController.stream;

  MethodChannelStonePayments() {
    methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case 'message':
          _paymentController.add(StatusTransaction(call.arguments));
          break;
        case 'transaction':
          _transactionController.add(call.arguments);
          break;
        case 'qrcode':
          _qrcodeController.add(call.arguments.replaceAll("\n", ""));
          break;
        default:
          _paymentController.add(call.arguments);
          break;
      }
    });
  }

  @override
  Future<String?> payment({
    required double value,
    required TypeTransactionEnum typeTransaction,
    int installment = 1,
    bool? printReceipt,
  }) async {
    final result = await methodChannel.invokeMethod<String>(
      'payment',
      <String, dynamic>{
        'value': value,
        'typeTransaction': typeTransaction.value,
        'installment': installment,
        'printReceipt': printReceipt,
      },
    );

    return result;
  }

  @override
  Future<String?> abortPayment() async {
    final result = await methodChannel.invokeMethod<String>(
      'abortPayment',
    );

    return result;
  }

  @override
  Future<String?> cancel({
    required String transactionId,
    bool? printReceipt,
  }) async {
    final result = await methodChannel.invokeMethod<String>(
      'cancel-payment',
      <String, dynamic>{
        'transactionId': transactionId,
        'printReceipt': printReceipt,
      },
    );

    return result;
  }

  @override
  Future<String?> activateStone({
    required String appName,
    required String stoneCode,
    List<String> stoneKeys = const [],
  }) async {
    final result = await methodChannel.invokeMethod<String>(
      'activateStone',
      <String, dynamic>{
        'appName': appName,
        'stoneCode': stoneCode,
        'stoneKeys': stoneKeys,
      },
    );

    return result;
  }

  @override
  Future<String?> printFile(String imgBase64) async {
    final result = await methodChannel.invokeMethod<String>(
      'printFile',
      <String, dynamic>{
        'imgBase64': imgBase64,
      },
    );

    return result;
  }

  @override
  Future<String?> print(List<ItemPrintModel> items) async {
    final result = await methodChannel.invokeMethod<String>(
      'print',
      <String, dynamic>{
        'items':
            items.map<Map<String, dynamic>>((item) => item.toMap()).toList(),
      },
    );

    return result;
  }

  @override
  Future<String?> printReceipt(TypeOwnerPrintEnum type) async {
    final result = await methodChannel.invokeMethod<String>(
      'printReceipt',
      <String, dynamic>{
        'type': type.value,
      },
    );

    return result;
  }
}
