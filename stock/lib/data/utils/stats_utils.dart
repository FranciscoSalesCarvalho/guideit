class StatsUtils {

  static double fixedPercentage(num? numb1, num? numb2) {
    return (((numb1 ?? 0.0) - (numb2 ?? 0.0)) * 100) / (numb2 ?? 1);
  }

  static double dailyPercentage(num? numb1, num? numb2) {
    return (((numb1 ?? 0.0) - (numb2 ?? 0.0)) * 100) / (numb1 ?? 1);
  }
}