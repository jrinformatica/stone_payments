import 'dart:async';

import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:stone_payments/enums/item_print_type_enum.dart';
import 'package:stone_payments/enums/status_transaction_enum.dart';
import 'package:stone_payments/enums/type_owner_print_enum.dart';
import 'package:stone_payments/enums/type_transaction_enum.dart';
import 'package:stone_payments/models/item_print_model.dart';
import 'package:stone_payments/models/transaction.dart';
import 'package:stone_payments/stone_payments.dart';
import 'package:stone_payments/stone_payments_method_channel.dart';
import 'package:stone_payments/stone_payments_platform_interface.dart';

class MockStonePaymentsPlatform
    with MockPlatformInterfaceMixin
    implements StonePaymentsPlatform {
  @override
  Future<String?> activateStone(
      {required String appName,
      required String stoneCode,
      required List<String> stoneKeys}) {
    return Future.value('Activated');
  }

  @override
  Stream<StatusTransaction> get onMessage =>
      Stream.value(const StatusTransaction("UNKNOWN"));

  @override
  Stream<String> get onQRCode => Stream.value("");

  @override
  Future<String?> printFile(String imgBase64) {
    return Future.value('Printed Image');
  }

  @override
  Future<Transaction> payment({
    required double value,
    required TypeTransactionEnum typeTransaction,
    int installment = 1,
    bool? printReceipt,
  }) {
    return Future.value(Transaction());
  }

  @override
  Future<String?> abortPayment() {
    return Future.value('true');
  }

  @override
  Future<String?> cancel({
    required String acquirerTransactionKey,
    bool? printReceipt,
  }) {
    return Future.value('true');
  }

  @override
  Future<String?> printReceipt(TypeOwnerPrintEnum type) {
    return Future.value('Printed Receipt');
  }

  @override
  Future<String?> print(List<ItemPrintModel> items) {
    return Future.value('Printed Items');
  }

  @override
  Future<Transaction> capture({required String transactionId}) {
    return Future.value(Transaction());
  }
}

void main() {
  late StonePaymentsPlatform initialPlatform;
  late StonePayments stonePaymentsPlugin;
  late MockStonePaymentsPlatform fakePlatform;

  setUp(() {
    TestWidgetsFlutterBinding.ensureInitialized();
    initialPlatform = StonePaymentsPlatform.instance;
    stonePaymentsPlugin = StonePayments();
    fakePlatform = MockStonePaymentsPlatform();
    StonePaymentsPlatform.instance = fakePlatform;
  });
  test('$MethodChannelStonePayments is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelStonePayments>());
  });

  group('StonePayments', () {
    test('payment should return status of payment', () async {
      double value = 100.00;
      TypeTransactionEnum typeTransaction = TypeTransactionEnum.credit;
      int installment = 1;
      bool printReceipt = false;

      Transaction? result = await stonePaymentsPlugin.payment(
        value: value,
        typeTransaction: typeTransaction,
        installment: installment,
        printReceipt: printReceipt,
      );

      expect(result, isA<Map<String, dynamic>>());
    });

    test('payment throws assertion error when value is not greater than 0',
        () async {
      expect(
        () async => await StonePayments().payment(
          value: -100.0,
          typeTransaction: TypeTransactionEnum.credit,
          installment: 1,
          printReceipt: true,
        ),
        throwsA(isA<AssertionError>()),
      );
    });

    test(
        'payment throws assertion error when installment is not greater than 0',
        () async {
      expect(
        () async => await StonePayments().payment(
          value: 100.0,
          typeTransaction: TypeTransactionEnum.credit,
          installment: 0,
          printReceipt: true,
        ),
        throwsA(isA<AssertionError>()),
      );
    });

    test(
        'payment throws assertion error when installment is greater than or equal to 13',
        () async {
      expect(
        () async => await StonePayments().payment(
          value: 100.0,
          typeTransaction: TypeTransactionEnum.credit,
          installment: 13,
          printReceipt: true,
        ),
        throwsA(isA<AssertionError>()),
      );
    });

    test('activateStone should return status of activation', () async {
      String appName = 'Test App';
      String stoneCode = '12345';

      await stonePaymentsPlugin.activateStone(
        appName: appName,
        stoneCode: stoneCode,
      );
    });

    test('printFile should return status of printing', () async {
      String imgBase64 = 'image in base64';

      expect(stonePaymentsPlugin.printFile(imgBase64), isA<void>());
    });

    test('print should return status of printing', () async {
      List<ItemPrintModel> items = [
        const ItemPrintModel(
          type: ItemPrintTypeEnum.text,
          data: 'Test',
        ),
        const ItemPrintModel(
          type: ItemPrintTypeEnum.image,
          data: 'ImageTest',
        ),
      ];

      await stonePaymentsPlugin.print(items);
    });

    test('printReceipt should return status of printing', () async {
      TypeOwnerPrintEnum type = TypeOwnerPrintEnum.client;

      await stonePaymentsPlugin.printReceipt(type);
    });
  });
}
