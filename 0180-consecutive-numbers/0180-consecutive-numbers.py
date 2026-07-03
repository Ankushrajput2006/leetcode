import pandas as pd

def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    result = logs[
        (logs["num"] == logs["num"].shift(1)) &
        (logs["num"] == logs["num"].shift(-1))
    ][["num"]].drop_duplicates()

    return result.rename(columns={"num": "ConsecutiveNums"})