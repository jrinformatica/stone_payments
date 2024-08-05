import 'package:flutter/services.dart';
import 'package:stone_payments/models/transaction.dart';

class StoneException<T> implements PlatformException {
  @override
  final String code;
  @override
  final String? message;
  @override
  final T? details;

  StoneException({
    required this.code,
    required this.message,
    this.details,
  });

  static StoneException fromPlatformException(
      PlatformException platformException) {
    switch (platformException.code) {
      case "PAYMENT_ERROR":
        return StoneException<Transaction>(
          code: platformException.code,
          message: platformException.message!,
          details: Transaction.fromJson(
            Map<String, dynamic>.from(platformException.details),
          ),
        );

      default:
        return StoneException(
          code: platformException.code,
          message: platformException.message!,
          details: platformException.details,
        );
    }
  }

  @override
  String toString() {
    return 'StoneException(code: $code, message: $message, details: $details)';
  }

  @override
  // TODO: implement stacktrace
  String? get stacktrace => throw UnimplementedError();
}
