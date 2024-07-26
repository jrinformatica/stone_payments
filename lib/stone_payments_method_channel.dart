import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:stone_payments/enums/status_transaction_enum.dart';
import 'package:stone_payments/enums/type_owner_print_enum.dart';
import 'package:stone_payments/models/transaction.dart';

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
  @override
  Stream<StatusTransaction> get onMessage => _paymentController.stream;

  @override
  Stream<String> get onQRCode => _qrcodeController.stream;

  MethodChannelStonePayments() {
    methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case 'message':
          _paymentController.add(StatusTransaction(call.arguments));
          break;
        case 'qrcode':
          _qrcodeController.add(call.arguments.replaceAll("\n", ""));
          break;
      }
    });
  }

  @override
  Future<Transaction> payment({
    required double value,
    required TypeTransactionEnum typeTransaction,
    int installment = 1,
    bool? printReceipt,
  }) async {
    final Map<String, dynamic>? json =
        Map<String, dynamic>.from(await methodChannel.invokeMethod(
      'payment',
      <String, dynamic>{
        'value': value,
        'typeTransaction': typeTransaction.value,
        'installment': installment,
        'printReceipt': false, //TODO: MUDAR DEPOIS
      },
    ));

    return Transaction.fromJson(json!);
  }

  @override
  Future<void> abortPayment() async {
    final result = await methodChannel.invokeMethod(
      'abortPayment',
    );

    return result;
  }

  @override
  Future<void> cancel({
    required String acquirerTransactionKey,
    bool? printReceipt,
  }) async {
    final result = await methodChannel.invokeMethod(
      'cancel-payment',
      <String, dynamic>{
        'acquirerTransactionKey': acquirerTransactionKey,
        'printReceipt': printReceipt,
      },
    );

    return result;
  }

  @override
  Future<void> activateStone({
    required String appName,
    required String stoneCode,
    List<String> stoneKeys = const [],
  }) async {
    final result = await methodChannel.invokeMethod(
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
  Future<void> printFile(String imgBase64) async {
    final result = await methodChannel.invokeMethod(
      'printFile',
      <String, dynamic>{
        'imgBase64': imgBase64,
      },
    );

    return result;
  }

  @override
  Future<void> print(List<ItemPrintModel> items) async {
    final result = await methodChannel.invokeMethod(
      'print',
      <String, dynamic>{
        'items':
            items.map<Map<String, dynamic>>((item) => item.toMap()).toList(),
      },
    );

    return result;
  }

  @override
  Future<void> printReceipt(TypeOwnerPrintEnum type) async {
    final result = await methodChannel.invokeMethod(
      'printReceipt',
      <String, dynamic>{
        'type': type.value,
      },
    );

    return result;
  }

  @override
  Future<Transaction> capture({required String transactionId}) async {
    final Map<String, dynamic> json =
        Map<String, dynamic>.from(await methodChannel.invokeMethod(
      'capture',
      <String, dynamic>{
        'transactionId': transactionId,
      },
    ));

    return Transaction.fromJson(json);
  }
}
