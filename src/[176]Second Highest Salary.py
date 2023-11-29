import pandas as pd

def second_highest_salary(employee: pd.DataFrame) -> pd.DataFrame:
    sort_list = sorted(employee["salary"].unique())
    second_highest_salary = sort_list[-2] if len(sort_list)>=2 else None
    return pd.DataFrame({"SecondHighestSalary":[second_highest_salary]})
