import 'package:json_annotation/json_annotation.dart';
import 'package:stone_payments/enums/type_transaction_enum.dart';
import 'package:stone_payments/enums/transaction_status_enum.dart';
import 'package:stone_payments/models/instalment_transaction_enum.dart';

part 'transaction.g.dart';

@JsonSerializable()
class Transaction {
  final String acquirerTransactionKey;
  final String? initiatorTransactionKey;
  final String amount;
  final TypeTransactionEnum typeOfTransaction;
  final InstalmentTransactionEnum instalmentTransaction;
  final String? instalmentType;
  final String? cardHolderNumber;
  final String? cardBrandName;
  final int? cardBrandId;
  final String? cardHolderName;
  final String? cardExpireDate;
  final String? authorizationCode;
  final TransactionStatusEnum transactionStatus;
  final String? date;
  final String? time;
  final String? entryMode;
  final String? aid;
  final String? arcq;
  final String? shortName;
  final String? userModel;
  final String? pinpadUsed;
  final String? balance;
  final bool isCapture;
  final String? subMerchantCategoryCode;
  final String? subMerchantAddress;
  final String? serialPos;

  Transaction({
    this.acquirerTransactionKey = "",
    this.initiatorTransactionKey = "",
    this.amount = "",
    this.typeOfTransaction = TypeTransactionEnum.credit,
    this.instalmentTransaction = InstalmentTransactionEnum.oneInstalment,
    this.instalmentType = "",
    this.cardHolderNumber = "",
    this.cardBrandName = "",
    this.cardBrandId,
    this.cardHolderName = "",
    this.cardExpireDate = "",
    this.authorizationCode = "",
    this.transactionStatus = TransactionStatusEnum.unknown,
    this.date = "",
    this.time = "",
    this.entryMode = "",
    this.aid = "",
    this.arcq = "",
    this.shortName = "",
    this.userModel = "",
    this.pinpadUsed = "",
    this.balance = "",
    this.isCapture = false,
    this.subMerchantCategoryCode = "",
    this.subMerchantAddress = "",
    this.serialPos = "",
  });

  factory Transaction.fromJson(Map<String, dynamic> json) =>
      _$TransactionFromJson(json);

  Map<String, dynamic> toJson() => _$TransactionToJson(this);
}
