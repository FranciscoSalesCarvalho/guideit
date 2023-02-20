import 'package:flutter/material.dart';

class StockTableCell extends StatelessWidget {
  final String _title;
  final bool isHeader;

  const StockTableCell(
    this._title, {
    Key? key,
    this.isHeader = true,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TableCell(
      verticalAlignment: TableCellVerticalAlignment.middle,
      child: Flexible(
        fit: FlexFit.tight,
        child: Padding(
          padding: const EdgeInsets.all(8),
          child: Center(
            child: Text(
              _title,
              style: TextStyle(
                fontWeight: isHeader ? FontWeight.bold : FontWeight.normal,
              ),
            ),
          ),
        ),
      ),
    );
  }
}
