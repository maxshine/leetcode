import pandas as pd

def nth_highest_salary(employee: pd.DataFrame, N: int) -> pd.DataFrame:
    sorted_list = sorted(employee['salary'].unique())
    desired_salary = sorted_list[0-N] if len(sorted_list) >= N else None
    return pd.DataFrame({f"getNthHighestSalary({N})":[desired_salary]})
