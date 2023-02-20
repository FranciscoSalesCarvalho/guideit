class Stock {
  Stock({
    required this.chart,
  });

  late final Chart chart;

  Stock.fromJson(Map<String, dynamic> json) {
    chart = Chart.fromJson(json['chart']);
  }
}

class Chart {
  Chart({
    required this.result,
    this.error,
  });

  late final List<Result> result;
  late final Null error;

  Chart.fromJson(Map<String, dynamic> json) {
    result = List.from(json['result']).map((e) => Result.fromJson(e)).toList();
    error = null;
  }
}

class Result {
  Result({
    required this.meta,
    required this.timestamp,
    required this.indicators,
  });

  late final Meta meta;
  late final List<int> timestamp;
  late final Indicators indicators;

  Result.fromJson(Map<String, dynamic> json) {
    meta = Meta.fromJson(json['meta']);
    timestamp = List.castFrom<dynamic, int>(json['timestamp']);
    indicators = Indicators.fromJson(json['indicators']);
  }
}

class Meta {
  Meta({
    required this.currency,
    required this.symbol,
    required this.exchangeName,
    required this.instrumentType,
    required this.firstTradeDate,
    required this.regularMarketTime,
    required this.gmtoffset,
    required this.timezone,
    required this.exchangeTimezoneName,
    required this.regularMarketPrice,
    required this.chartPreviousClose,
    required this.priceHint,
    required this.currentTradingPeriod,
    required this.dataGranularity,
    required this.range,
    required this.validRanges,
  });

  late final String currency;
  late final String symbol;
  late final String exchangeName;
  late final String instrumentType;
  late final int firstTradeDate;
  late final int regularMarketTime;
  late final int gmtoffset;
  late final String timezone;
  late final String exchangeTimezoneName;
  late final double regularMarketPrice;
  late final double chartPreviousClose;
  late final int priceHint;
  late final CurrentTradingPeriod currentTradingPeriod;
  late final String dataGranularity;
  late final String range;
  late final List<String> validRanges;

  Meta.fromJson(Map<String, dynamic> json) {
    currency = json['currency'];
    symbol = json['symbol'];
    exchangeName = json['exchangeName'];
    instrumentType = json['instrumentType'];
    firstTradeDate = json['firstTradeDate'];
    regularMarketTime = json['regularMarketTime'];
    gmtoffset = json['gmtoffset'];
    timezone = json['timezone'];
    exchangeTimezoneName = json['exchangeTimezoneName'];
    regularMarketPrice = json['regularMarketPrice'];
    chartPreviousClose = json['chartPreviousClose'];
    priceHint = json['priceHint'];
    currentTradingPeriod =
        CurrentTradingPeriod.fromJson(json['currentTradingPeriod']);
    dataGranularity = json['dataGranularity'];
    range = json['range'];
    validRanges = List.castFrom<dynamic, String>(json['validRanges']);
  }
}

class CurrentTradingPeriod {
  CurrentTradingPeriod({
    required this.pre,
    required this.regular,
    required this.post,
  });

  late final Pre pre;
  late final Regular regular;
  late final Post post;

  CurrentTradingPeriod.fromJson(Map<String, dynamic> json) {
    pre = Pre.fromJson(json['pre']);
    regular = Regular.fromJson(json['regular']);
    post = Post.fromJson(json['post']);
  }
}

class Pre {
  Pre({
    required this.timezone,
    required this.end,
    required this.start,
    required this.gmtoffset,
  });

  late final String timezone;
  late final int end;
  late final int start;
  late final int gmtoffset;

  Pre.fromJson(Map<String, dynamic> json) {
    timezone = json['timezone'];
    end = json['end'];
    start = json['start'];
    gmtoffset = json['gmtoffset'];
  }
}

class Regular {
  Regular({
    required this.timezone,
    required this.end,
    required this.start,
    required this.gmtoffset,
  });

  late final String timezone;
  late final int end;
  late final int start;
  late final int gmtoffset;

  Regular.fromJson(Map<String, dynamic> json) {
    timezone = json['timezone'];
    end = json['end'];
    start = json['start'];
    gmtoffset = json['gmtoffset'];
  }
}

class Post {
  Post({
    required this.timezone,
    required this.end,
    required this.start,
    required this.gmtoffset,
  });

  late final String timezone;
  late final int end;
  late final int start;
  late final int gmtoffset;

  Post.fromJson(Map<String, dynamic> json) {
    timezone = json['timezone'];
    end = json['end'];
    start = json['start'];
    gmtoffset = json['gmtoffset'];
  }
}

class Indicators {
  Indicators({
    required this.quote,
    required this.adjclose,
  });

  late final List<Quote> quote;
  late final List<Adjclose> adjclose;

  Indicators.fromJson(Map<String, dynamic> json) {
    quote = List.from(json['quote']).map((e) => Quote.fromJson(e)).toList();
    adjclose =
        List.from(json['adjclose']).map((e) => Adjclose.fromJson(e)).toList();
  }
}

class Quote {
  Quote({
    required this.volume,
    required this.open,
    required this.close,
    required this.low,
    required this.high,
  });

  late final List<int> volume;
  late final List<num> open;
  late final List<num> close;
  late final List<num> low;
  late final List<num> high;

  Quote.fromJson(Map<String, dynamic> json) {
    volume = List.castFrom<dynamic, int>(json['volume']);
    open = List.castFrom<dynamic, num>(json['open']);
    close = List.castFrom<dynamic, num>(json['close']);
    low = List.castFrom<dynamic, num>(json['low']);
    high = List.castFrom<dynamic, num>(json['high']);
  }
}

class Adjclose {
  Adjclose({
    required this.adjclose,
  });

  late final List<double> adjclose;

  Adjclose.fromJson(Map<String, dynamic> json){
    adjclose = List.castFrom<dynamic, double>(json['adjclose']);
  }
}
