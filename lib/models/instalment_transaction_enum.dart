import 'package:json_annotation/json_annotation.dart';

@JsonEnum(fieldRename: FieldRename.screamingSnake)
enum InstalmentTransactionEnum {
  oneInstalment(1, false),
  twoInstalmentNoInterest(2, false),
  threeInstalmentNoInterest(3, false),
  fourInstalmentNoInterest(4, false),
  fiveInstalmentNoInterest(5, false),
  sixInstalmentNoInterest(6, false),
  sevenInstalmentNoInterest(7, false),
  eightInstalmentNoInterest(8, false),
  nineInstalmentNoInterest(9, false),
  tenInstalmentNoInterest(10, false),
  elevenInstalmentNoInterest(11, false),
  twelveInstalmentNoInterest(12, false),
  thirteenInstalmentNoInterest(13, false),
  fourteenInstalmentNoInterest(14, false),
  fifteenInstalmentNoInterest(15, false),
  sixteenInstalmentNoInterest(16, false),
  seventeenInstalmentNoInterest(17, false),
  eighteenInstalmentNoInterest(18, false),
  twoInstalmentWithInterest(2, true),
  threeInstalmentWithInterest(3, true),
  fourInstalmentWithInterest(4, true),
  fiveInstalmentWithInterest(5, true),
  sixInstalmentWithInterest(6, true),
  sevenInstalmentWithInterest(7, true),
  eightInstalmentWithInterest(8, true),
  nineInstalmentWithInterest(9, true),
  tenInstalmentWithInterest(10, true),
  elevenInstalmentWithInterest(11, true),
  twelveInstalmentWithInterest(12, true),
  thirteenInstalmentWithInterest(13, true),
  fourteenInstalmentWithInterest(14, true),
  fifteenInstalmentWithInterest(15, true),
  sixteenInstalmentWithInterest(16, true),
  seventeenInstalmentWithInterest(17, true),
  eighteenInstalmentWithInterest(18, true);

  const InstalmentTransactionEnum(this.quantidadeParcelas, this.temJuros);
  final int quantidadeParcelas;
  final bool temJuros;
}
