import 'package:json_annotation/json_annotation.dart';

@JsonEnum(fieldRename: FieldRename.screamingSnake)
enum StatusTransaction {
  transactionWaitingCard, //	Aguardando o cartão ser inserido
  transactionWaitingPassword, //	Aguardando a senha do cartão
  transactionSending, //	Enviando a transação para a Stone
  transactionRemoveCard, //	Aguardando o cartão ser retirado (Somente para Chip e Senha)
  transactionCardRemoved, //	Indica que o cartão foi removido
  reversingTransactionWithError, //	Tentando reverter transação com status WITH_ERROR
  transactionTypeSelection, //	Usado quando necessário escolher entre modalidades. Por exemplo: Alimentação e Refeição no mesmo cartão
  transactionWaitingQrcodeScan, //	Usado para mostrar o QrCode e aguardar sua leitura
}

const Map<String, StatusTransaction> statusTransactionEnumMap =
    <String, StatusTransaction>{
  'TRANSACTION_WAITING_CARD': StatusTransaction.transactionWaitingCard,
  'TRANSACTION_WAITING_PASSWORD': StatusTransaction.transactionWaitingPassword,
  'TRANSACTION_SENDING': StatusTransaction.transactionSending,
  'TRANSACTION_REMOVE_CARD': StatusTransaction.transactionRemoveCard,
  'TRANSACTION_CARD_REMOVED': StatusTransaction.transactionCardRemoved,
  'REVERSING_TRANSACTION_WITH_ERROR':
      StatusTransaction.reversingTransactionWithError,
  'TRANSACTION_TYPE_SELECTION': StatusTransaction.transactionTypeSelection,
  'TRANSACTION_WAITING_QRCODE_SCAN':
      StatusTransaction.transactionWaitingQrcodeScan,
};
