import pandas as pd

def find_employees(employee: pd.DataFrame) -> pd.DataFrame:
    df = employee.merge(employee, left_on="managerId", right_on="id", how="left", suffixes=("_emp", "_mgr"))
    names_series = df[(~df["id_mgr"].isnull()) & (df["salary_emp"]>df["salary_mgr"])]["name_emp"]
    return pd.DataFrame({"Employee":names_series})
