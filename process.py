
    import os
    from datetime import datetime, timedelta
    import sys


    import pandas as pd
    sys.path.append(r"\\192.168.1.20\Rmc\Data Analysis\Dropbox\Model_AUTO")
    from package.skype import skp_send

try:
    # Skype 連線資訊
    pass_dir = r"\\192.168.1.20\Rmc\Data Analysis\Dropbox\Model_AUTO\log\pass\pass.xlsx"
    login = str(pd.read_excel(pass_dir).query("item=='skype'")["userid"][0])
    pwd = pd.read_excel(pass_dir).query("item=='skype'")["pwd"][0]

    today = datetime.today()
    if today.strftime('%a') == 'Mon':
        dateT = (today - timedelta(days=1)).strftime('%Y-%m-%d')
        dateY = (today - timedelta(days=3)).strftime('%Y-%m-%d')
    else:
        dateT = (today - timedelta(days=1)).strftime('%Y-%m-%d')
        dateY = dateT

    def check_files_existence(folder_path, file_prefixes):
        files = os.listdir(folder_path)
        missing_files = []
        for prefix in file_prefixes:
            file_exists = any(file.startswith(prefix) for file in files)
            if not file_exists:
                missing_files.append(prefix)

        missing_dbs = set()
        missing_data = set()

        for missing_file in missing_files:
            DB = missing_file.split("_")[-2]
            missing_dbs.add(DB)
            if "Equity" in missing_file:
                missing_data.add(f"{DB} Equity")
            elif "Accounts" in missing_file:
                missing_data.add(f"{DB} Accounts")
            elif "Raw" in missing_file:
                missing_data.add(f"{DB} Raw")

        if len(missing_dbs) == 0:
            skp_send(login, pwd, who='auto_fail',tag= 'Wilson',content="小程序 All Clear")
        else:
            missing_dbs_msg = ", ".join(missing_dbs)
            missing_data_msg = ", ".join(missing_data)
            skp_send(login, pwd, who='auto_fail', tag= 'Wilson' ,content=f"小程序Missing DB: ({missing_data_msg})")
            


    YY = today.strftime("%Y")
    YM = today.strftime("%m")
    YD = today.strftime("%d")

    # 指定要检查的文件夹路径
    folder_path = fr"\\192.168.1.20\Rmc\Data Analysis\process_new\{YY}{YM}{YD}"


    # 指定文件前缀
    file_prefixes = [
        "Accounts_Report_AU_VFSC2", "Equity_Report_AU_VFSC2", "Raw_Report_AU_VFSC2",
        "Accounts_Report_AU2_VFSC2", "Equity_Report_AU2_VFSC2", "Raw_Report_AU2_VFSC2",
        "Accounts_Report_AU3_VFSC2", "Equity_Report_AU3_VFSC2", "Raw_Report_AU3_VFSC2",
        "Accounts_Report_AU4_VFSC2", "Equity_Report_AU4_VFSC2", "Raw_Report_AU4_VFSC2",
        "Accounts_Report_AU5_VFSC2", "Equity_Report_AU5_VFSC2", "Raw_Report_AU5_VFSC2",
        "Accounts_Report_AU6_VFSC2", "Equity_Report_AU6_VFSC2", "Raw_Report_AU6_VFSC2",
        "Accounts_Report_UK_VFSC2", "Equity_Report_UK_VFSC2", "Raw_Report_UK_VFSC2",
        "Accounts_Report_UK2_VFSC2", "Equity_Report_UK2_VFSC2", "Raw_Report_UK2_VFSC2",
        "Accounts_Report_UK3_VFSC2", "Equity_Report_UK3_VFSC2", "Raw_Report_UK3_VFSC2",
        "Accounts_Report_UK4_VFSC2", "Equity_Report_UK4_VFSC2", "Raw_Report_UK4_VFSC2",
        "Accounts_Report_UK5_VFSC2", "Equity_Report_UK5_VFSC2", "Raw_Report_UK5_VFSC2",
        "Accounts_Report_UK6_VFSC2", "Equity_Report_UK6_VFSC2", "Raw_Report_UK6_VFSC2",
        "Accounts_Report_UK7_VFSC2", "Equity_Report_UK7_VFSC2", "Raw_Report_UK7_VFSC2",
        "Accounts_Report_UK8_VFSC2", "Equity_Report_UK8_VFSC2", "Raw_Report_UK8_VFSC2",

        "Accounts_Report_VT", "Equity_Report_VT", "Raw_Report_VT",
        "Accounts_Report_VT2", "Equity_Report_VT2", "Raw_Report_VT2",
        "Accounts_Report_VT3", "Equity_Report_VT3", "Raw_Report_VT3",
        "Accounts_Report_VT4", "Equity_Report_VT4", "Raw_Report_VT4",
        "Accounts_Report_VT5", "Equity_Report_VT5", "Raw_Report_VT5",






    ]



    check_files_existence(folder_path, file_prefixes)
except Exception as e:
    print(e)
    input()