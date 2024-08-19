import 'package:json_annotation/json_annotation.dart';

@JsonEnum(valueField: "index")
enum TypeTransactionEnum {
  debit("Débito", 'DEBITO'),
  credit("Crédito", 'CREDITO'),
  voucher("Voucher", 'VOUCHER'),
  instantPayment("Pagamento Instantâneo", 'PAGAMENTO INSTANTANEO'),
  pix("Pix", 'PIX');

  final String nome;
  final String normalizedName;
  const TypeTransactionEnum(this.nome, this.normalizedName);
}
