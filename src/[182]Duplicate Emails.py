import pandas as pd

def duplicate_emails(person: pd.DataFrame) -> pd.DataFrame:
    df1 = person.groupby(by="email", as_index=False).count()
    return pd.DataFrame({"Email":df1[df1["id"] >= 2]["email"]})
