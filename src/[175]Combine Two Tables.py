import pandas as pd

def combine_two_tables(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    ret = person.merge(address, on=["personId"], how="left")
    return ret[["firstName", "lastName", "city", "state"]]
