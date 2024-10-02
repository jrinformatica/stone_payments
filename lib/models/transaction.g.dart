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
      instalmentTransaction: $enumDecodeNullable(
              _$InstalmentTransactionEnumEnumMap,
              json['instalmentTransaction']) ??
          InstalmentTransactionEnum.oneInstalment,
      instalmentType: json['instalmentType'] as String? ?? "",
      cardHolderNumber: json['cardHolderNumber'] as String? ?? "",
      cardBrandName: json['cardBrandName'] as String? ?? "",
      cardBrandId: (json['cardBrandId'] as num?)?.toInt(),
      cardHolderName: json['cardHolderName'] as String? ?? "",
      cardExpireDate: json['cardExpireDate'] as String? ?? "",
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
      serialPos: json['serialPos'] as String? ?? "",
    );

Map<String, dynamic> _$TransactionToJson(Transaction instance) =>
    <String, dynamic>{
      'acquirerTransactionKey': instance.acquirerTransactionKey,
      'initiatorTransactionKey': instance.initiatorTransactionKey,
      'amount': instance.amount,
      'typeOfTransaction':
          _$TypeTransactionEnumEnumMap[instance.typeOfTransaction]!,
      'instalmentTransaction':
          _$InstalmentTransactionEnumEnumMap[instance.instalmentTransaction]!,
      'instalmentType': instance.instalmentType,
      'cardHolderNumber': instance.cardHolderNumber,
      'cardBrandName': instance.cardBrandName,
      'cardBrandId': instance.cardBrandId,
      'cardHolderName': instance.cardHolderName,
      'cardExpireDate': instance.cardExpireDate,
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
      'serialPos': instance.serialPos,
    };

const _$TypeTransactionEnumEnumMap = {
  TypeTransactionEnum.debit: 0,
  TypeTransactionEnum.credit: 1,
  TypeTransactionEnum.voucher: 2,
  TypeTransactionEnum.instantPayment: 3,
  TypeTransactionEnum.pix: 4,
};

const _$InstalmentTransactionEnumEnumMap = {
  InstalmentTransactionEnum.oneInstalment: 'ONE_INSTALMENT',
  InstalmentTransactionEnum.twoInstalmentNoInterest:
      'TWO_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.threeInstalmentNoInterest:
      'THREE_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.fourInstalmentNoInterest:
      'FOUR_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.fiveInstalmentNoInterest:
      'FIVE_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.sixInstalmentNoInterest:
      'SIX_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.sevenInstalmentNoInterest:
      'SEVEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.eightInstalmentNoInterest:
      'EIGHT_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.nineInstalmentNoInterest:
      'NINE_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.tenInstalmentNoInterest:
      'TEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.elevenInstalmentNoInterest:
      'ELEVEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.twelveInstalmentNoInterest:
      'TWELVE_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.thirteenInstalmentNoInterest:
      'THIRTEEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.fourteenInstalmentNoInterest:
      'FOURTEEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.fifteenInstalmentNoInterest:
      'FIFTEEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.sixteenInstalmentNoInterest:
      'SIXTEEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.seventeenInstalmentNoInterest:
      'SEVENTEEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.eighteenInstalmentNoInterest:
      'EIGHTEEN_INSTALMENT_NO_INTEREST',
  InstalmentTransactionEnum.twoInstalmentWithInterest:
      'TWO_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.threeInstalmentWithInterest:
      'THREE_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.fourInstalmentWithInterest:
      'FOUR_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.fiveInstalmentWithInterest:
      'FIVE_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.sixInstalmentWithInterest:
      'SIX_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.sevenInstalmentWithInterest:
      'SEVEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.eightInstalmentWithInterest:
      'EIGHT_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.nineInstalmentWithInterest:
      'NINE_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.tenInstalmentWithInterest:
      'TEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.elevenInstalmentWithInterest:
      'ELEVEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.twelveInstalmentWithInterest:
      'TWELVE_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.thirteenInstalmentWithInterest:
      'THIRTEEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.fourteenInstalmentWithInterest:
      'FOURTEEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.fifteenInstalmentWithInterest:
      'FIFTEEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.sixteenInstalmentWithInterest:
      'SIXTEEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.seventeenInstalmentWithInterest:
      'SEVENTEEN_INSTALMENT_WITH_INTEREST',
  InstalmentTransactionEnum.eighteenInstalmentWithInterest:
      'EIGHTEEN_INSTALMENT_WITH_INTEREST',
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
