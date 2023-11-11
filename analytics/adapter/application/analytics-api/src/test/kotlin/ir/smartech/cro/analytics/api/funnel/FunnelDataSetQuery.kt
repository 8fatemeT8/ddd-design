package ir.smartech.cro.analytics.api.funnel

class FunnelDataSetQuery {

    companion object {
        fun insertData(values: String) =
            "INSERT INTO analytics.intrack_events (product_id, user_id, timestamp, event_name, string1,numeric1) VALUES $values"

        fun firstDataSet(): String = "(2, '1', 1675323660, 'event1', 'iOS', 0)," +
                "       (2, '2', 1675320120, 'event1', 'android', 0)," +
                "       (2, '3', 1675331520, 'event1', 'linux',0)," +
                "       (2, '4', 1675313220, 'event1', 'android',0)," +
                "       (2, '5', 1675325880, 'event1', 'macOS', 0)," +
                "       (2, '6', 1675338120, 'event1', 'iOS', 0)," +
                "       (2, '7', 1675324020, 'event1', 'windows', 0)," +
                "       (2, '1', 1675324920, 'event2', 'iOS', 0)," +
                "       (2, '2', 1675337100, 'event2', 'windows', 0)," +
                "       (2, '3', 1675337100, 'event2', 'linux', 0)," +
                "       (2, '4', 1675318380, 'event2', 'windows', 0)," +
                "       (2, '5', 1675326120, 'event2', 'macOS', 0)," +
                "       (2, '6', 1675341600, 'event2', 'android',0)," +
                "       (2, '7', 1675325280, 'event2', 'windows', 0)," +
                "       (2, '1', 1675325100, 'event3', 'iOS', 0)," +
                "       (2, '2', 1675341300, 'event3', 'windows', 0)," +
                "       (2, '4', 1675321380, 'event3', 'windows', 0)," +
                "       (2, '5', 1675326300, 'event3', 'macOS', 0)," +
                "       (2, '6', 1675341900, 'event3', 'android', 0)," +
                "       (2, '7', 1675325460, 'event3', 'windows', 0)," +
                "       (2, '1', 1675325880, 'event4', 'iOS', 2000000)," +
                "       (2, '2', 1675353300, 'event4', 'android', 200000)," +
                "       (2, '5', 1675326540, 'event4', 'macOS', 50000)," +
                "       (2, '6', 1675844580, 'event4', 'android', 40000)," +
                "       (2, '7', 1675326240, 'event4', 'windows', 300000);"

        fun secondDataSet(): String = "(2, '1', 1675323660, 'event1', 'iOS', 0)," +
                "       (2, '2', 1675320120, 'event1', 'android', 0)," +
                "       (2, '3', 1675331520, 'event1', 'linux',0)," +
                "       (2, '4', 1675313220, 'event1', 'android',0)," +
                "       (2, '5', 1675325880, 'event1', 'macOS', 0)," +
                "       (3, '6', 1675338120, 'event1', 'iOS', 0)," +
                "       (2, '7', 1675324020, 'event1', 'windows', 0)," +
                "       (2, '1', 1675324920, 'event2', 'iOS', 0)," +
                "       (2, '2', 1675337100, 'event2', 'windows', 0)," +
                "       (2, '3', 1675337100, 'event2', 'linux', 0)," +
                "       (2, '4', 1675318380, 'event2', 'windows', 0)," +
                "       (2, '5', 1675326120, 'event2', 'macOS', 0)," +
                "       (3, '6', 1675341600, 'event2', 'android',0)," +
                "       (2, '7', 1675325280, 'event2', 'windows', 0)," +
                "       (2, '1', 1675325100, 'event3', 'iOS', 0)," +
                "       (2, '2', 1675341300, 'event3', 'windows', 0)," +
                "       (2, '4', 1675321380, 'event3', 'windows', 0)," +
                "       (2, '5', 1675326300, 'event3', 'macOS', 0)," +
                "       (3, '6', 1675341900, 'event3', 'android', 0)," +
                "       (2, '7', 1675325460, 'event3', 'windows', 0)," +
                "       (2, '1', 1675325880, 'event4', 'iOS', 2000000)," +
                "       (2, '2', 1675353300, 'event4', 'android', 200000)," +
                "       (2, '5', 1675326540, 'event4', 'macOS', 50000)," +
                "       (3, '6', 1675844580, 'event4', 'android', 40000)," +
                "       (2, '7', 1675326240, 'event4', 'windows', 300000);"
    }
}