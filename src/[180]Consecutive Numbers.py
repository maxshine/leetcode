import pandas as pd

def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    logs["count_consecutive"] = logs.rolling(window=3).apply(lambda x: x.nunique())["num"]
    return pandas.DataFrame({"ConsecutiveNums": logs[logs["count_consecutive"]==1.0]["num"].unique()})
