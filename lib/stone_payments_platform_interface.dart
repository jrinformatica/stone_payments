import 'dart:typed_data';

import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:stone_payments/enums/type_owner_print_enum.dart';
import 'package:stone_payments/enums/type_transaction_enum.dart';
import 'package:stone_payments/models/item_print_model.dart';
import 'package:stone_payments/models/transaction.dart';

import 'enums/action_transaciton_enum.dart';
import 'stone_payments_method_channel.dart';

abstract class StonePaymentsPlatform extends PlatformInterface {
  /// Constructs a StonePaymentsPlatform.
  StonePaymentsPlatform() : super(token: _token);

  static final Object _token = Object();

  static StonePaymentsPlatform _instance = MethodChannelStonePayments();

  /// The default instance of [StonePaymentsPlatform] to use.
  ///
  /// Defaults to [MethodChannelStonePayments].
  static StonePaymentsPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [StonePaymentsPlatform] when
  /// they register themselves.
  static set instance(StonePaymentsPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Stream<ActionTransacitonEnum> get onPaymentStatus;

  Stream<Uint8List> get onQRCode;

  Future<Transaction> payment({
    required double value,
    required TypeTransactionEnum typeTransaction,
    int installment = 1,
    bool? printReceipt,
  }) {
    throw UnimplementedError('payment() has not been implemented.');
  }

  Future<void> abortPayment() {
    throw UnimplementedError('abortPayment() has not been implemented.');
  }

  Future<void> cancel({
    required String acquirerTransactionKey,
    bool? printReceipt,
  });

  Future<void> activateStone({
    required String appName,
    required String stoneCode,
    String? qrCodeAuthorization,
    String? qrCodeProviderId,
  });

  Future<void> printFile(String imgBase64);

  Future<void> print(List<ItemPrintModel> items);

  Future<void> printReceipt(TypeOwnerPrintEnum type);

  Future<Transaction> capture({
    required String acquirerTransactionKey,
  });
}
