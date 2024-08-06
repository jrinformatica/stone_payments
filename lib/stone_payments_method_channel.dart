import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:rxdart/rxdart.dart';
import 'package:stone_payments/enums/type_owner_print_enum.dart';
import 'package:stone_payments/exceptions/stone_exception.dart';
import 'package:stone_payments/models/transaction.dart';

import 'enums/action_transaciton_enum.dart';
import 'enums/type_transaction_enum.dart';
import 'models/item_print_model.dart';
import 'stone_payments_platform_interface.dart';

/// An implementation of [StonePaymentsPlatform] that uses method channels.
class MethodChannelStonePayments extends StonePaymentsPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('stone_payments');

  final _paymentController = BehaviorSubject<ActionTransacitonEnum>();
  final _qrcodeController = BehaviorSubject<Uint8List>();

  @override
  Stream<ActionTransacitonEnum> get onPaymentStatus =>
      _paymentController.stream;

  @override
  Stream<Uint8List> get onQRCode => _qrcodeController.stream;

  MethodChannelStonePayments() {
    methodChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case 'payment-action':
          String status = call.arguments;
          _paymentController.add(actionTransactionEnumMap[status]!);
          break;
        case 'qrcode':
          _qrcodeController.add(call.arguments);
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
    try {
      final Map<String, dynamic> json =
          Map<String, dynamic>.from(await methodChannel.invokeMethod(
        'payment',
        <String, dynamic>{
          'value': value,
          'typeTransaction': typeTransaction.index,
          'installment': installment,
          'printReceipt': printReceipt,
        },
      ));

      return Transaction.fromJson(json);
    } on PlatformException catch (e) {
      throw StoneException.fromPlatformException(e);
    }
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
    String? qrCodeAuthorization,
    String? qrCodeProviderId,
  }) async {
    final result = await methodChannel.invokeMethod(
      'activateStone',
      <String, dynamic>{
        'appName': appName,
        'stoneCode': stoneCode,
        'qrCodeAuthorization': qrCodeAuthorization,
        'qrCodeProviderId': qrCodeProviderId,
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
  Future<Transaction> capture({required String acquirerTransactionKey}) async {
    final Map<String, dynamic> json =
        Map<String, dynamic>.from(await methodChannel.invokeMethod(
      'capture',
      <String, dynamic>{
        'acquirerTransactionKey': acquirerTransactionKey,
      },
    ));

    return Transaction.fromJson(json);
  }
}
