import 'package:flutter/material.dart';
import 'package:stock/data/model/stock.dart';
import 'package:stock/data/service/stock_service.dart';
import 'package:stock/data/utils/stats_extensions.dart';
import 'package:stock/data/utils/stats_utils.dart';
import 'package:stock/widgets/error.dart';
import 'package:stock/widgets/loading.dart';
import 'package:stock/widgets/stock_table_cell.dart';

class InformationPage extends StatelessWidget {
  const InformationPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Stock Information'),
      ),
      body: FutureBuilder<Stock>(
        future: StockService().getInfo("PETR4.SA"),
        builder: (BuildContext context, AsyncSnapshot<Stock> snapshot) {
          if (snapshot.hasData) {
            debugPrint(snapshot.data.toString());
            return SingleChildScrollView(
              child: Table(
                border: TableBorder.all(),
                columnWidths: const <int, TableColumnWidth>{
                  0: IntrinsicColumnWidth(),
                  1: IntrinsicColumnWidth(),
                  2: IntrinsicColumnWidth(),
                  3: FlexColumnWidth(),
                  4: FlexColumnWidth()
                },
                defaultVerticalAlignment: TableCellVerticalAlignment.middle,
                children: [
                  const TableRow(
                    children: <Widget>[
                      StockTableCell('Dia'),
                      StockTableCell('Data'),
                      StockTableCell('Valor'),
                      StockTableCell('Variação em relação a D-1'),
                      StockTableCell('Variação em relação a primeira data'),
                    ],
                  ),
                  ..._bodyTable(snapshot.data?.chart.result[0])
                ],
              ),
            );
          } else if (snapshot.hasError) {
            return StockError(snapshot.error);
          } else {
            return const StockLoading();
          }
        },
      ),
    );
  }

  List<TableRow> _bodyTable(Result? result) {
    var length = (result?.timestamp.length ?? 0) - 1;

    return List.generate(length, (i) {
      num percentageFixed = StatsUtils.fixedPercentage(
        result?.indicators.quote[0].open[i],
        result?.indicators.quote[0].open[0],
      );

      num percentageDay = (i == 0)
          ? 0
          : StatsUtils.dailyPercentage(
              result?.indicators.quote[0].open[i],
              result?.indicators.quote[0].open[i - 1],
            );

      return TableRow(
        children: <Widget>[
          StockTableCell('$i', isHeader: false),
          StockTableCell(
            (result?.timestamp[i] ?? 0).toBrazilianDate(),
            isHeader: false,
          ),
          StockTableCell(
            "${result?.indicators.quote[0].open[i].toBrazilianMoney()}",
            isHeader: false,
          ),
          StockTableCell(
            "${percentageDay.toStringAsFixed(2)}%",
            isHeader: false,
          ),
          StockTableCell(
            "${percentageFixed.toStringAsFixed(2)}%",
            isHeader: false,
          ),
        ],
      );
    }).toList();
  }
}
