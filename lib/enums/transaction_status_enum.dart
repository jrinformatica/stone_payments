import 'package:json_annotation/json_annotation.dart';

@JsonEnum(fieldRename: FieldRename.screamingSnake)
enum TransactionStatusEnum {
  unknown,
  approved,
  declined,
  declinedByCard,
  cancelled,
  technicalError,
  rejected,
  withError,
  pendingReversal,
  pending,
  reversed;
}
