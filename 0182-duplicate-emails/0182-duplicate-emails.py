import pandas as pd

def duplicate_emails(person: pd.DataFrame) -> pd.DataFrame:
    return (
        person.groupby("email")
              .size()
              .reset_index(name="count")
              .query("count > 1")[["email"]]
              .rename(columns={"email": "Email"})
    )