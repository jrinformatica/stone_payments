// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'transaction.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Transaction _$TransactionFromJson(Map<String, dynamic> json) => Transaction(
      acquirerTransactionKey: json['acquirerTransactionKey'] as String? ?? "",
      initiatorTransactionKey: json['initiatorTransactionKey'] as String? ?? "",
      amount: json['amount'] as String? ?? "",
      typeOfTransaction: $enumDecodeNullable(
              _$TypeTransactionEnumEnumMap, json['typeOfTransaction']) ??
          TypeTransactionEnum.credit,
      instalmentTransaction: json['instalmentTransaction'] as String? ?? "",
      instalmentType: json['instalmentType'] as String? ?? "",
      cardHolderNumber: json['cardHolderNumber'] as String? ?? "",
      cardBrandName: json['cardBrandName'] as String? ?? "",
      cardHolderName: json['cardHolderName'] as String? ?? "",
      authorizationCode: json['authorizationCode'] as String? ?? "",
      transactionStatus: $enumDecodeNullable(
              _$TransactionStatusEnumEnumMap, json['transactionStatus']) ??
          TransactionStatusEnum.unknown,
      date: json['date'] as String? ?? "",
      time: json['time'] as String? ?? "",
      entryMode: json['entryMode'] as String? ?? "",
      aid: json['aid'] as String? ?? "",
      arcq: json['arcq'] as String? ?? "",
      shortName: json['shortName'] as String? ?? "",
      userModel: json['userModel'] as String? ?? "",
      pinpadUsed: json['pinpadUsed'] as String? ?? "",
      balance: json['balance'] as String? ?? "",
      isCapture: json['isCapture'] as bool? ?? false,
      subMerchantCategoryCode: json['subMerchantCategoryCode'] as String? ?? "",
      subMerchantAddress: json['subMerchantAddress'] as String? ?? "",
    );

Map<String, dynamic> _$TransactionToJson(Transaction instance) =>
    <String, dynamic>{
      'acquirerTransactionKey': instance.acquirerTransactionKey,
      'initiatorTransactionKey': instance.initiatorTransactionKey,
      'amount': instance.amount,
      'typeOfTransaction':
          _$TypeTransactionEnumEnumMap[instance.typeOfTransaction]!,
      'instalmentTransaction': instance.instalmentTransaction,
      'instalmentType': instance.instalmentType,
      'cardHolderNumber': instance.cardHolderNumber,
      'cardBrandName': instance.cardBrandName,
      'cardHolderName': instance.cardHolderName,
      'authorizationCode': instance.authorizationCode,
      'transactionStatus':
          _$TransactionStatusEnumEnumMap[instance.transactionStatus]!,
      'date': instance.date,
      'time': instance.time,
      'entryMode': instance.entryMode,
      'aid': instance.aid,
      'arcq': instance.arcq,
      'shortName': instance.shortName,
      'userModel': instance.userModel,
      'pinpadUsed': instance.pinpadUsed,
      'balance': instance.balance,
      'isCapture': instance.isCapture,
      'subMerchantCategoryCode': instance.subMerchantCategoryCode,
      'subMerchantAddress': instance.subMerchantAddress,
    };

const _$TypeTransactionEnumEnumMap = {
  TypeTransactionEnum.debit: 0,
  TypeTransactionEnum.credit: 1,
  TypeTransactionEnum.voucher: 2,
  TypeTransactionEnum.instantPayment: 3,
  TypeTransactionEnum.pix: 4,
};

const _$TransactionStatusEnumEnumMap = {
  TransactionStatusEnum.unknown: 'UNKNOWN',
  TransactionStatusEnum.approved: 'APPROVED',
  TransactionStatusEnum.declined: 'DECLINED',
  TransactionStatusEnum.declinedByCard: 'DECLINED_BY_CARD',
  TransactionStatusEnum.cancelled: 'CANCELLED',
  TransactionStatusEnum.technicalError: 'TECHNICAL_ERROR',
  TransactionStatusEnum.rejected: 'REJECTED',
  TransactionStatusEnum.withError: 'WITH_ERROR',
  TransactionStatusEnum.pendingReversal: 'PENDING_REVERSAL',
  TransactionStatusEnum.pending: 'PENDING',
  TransactionStatusEnum.reversed: 'REVERSED',
};
