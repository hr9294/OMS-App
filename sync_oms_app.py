import subprocess
import sys
import os

clones = [
    r"D:\Automation & Load Testing\Appium\Code\OMS-App",
    r"C:\Users\hrrah\git\OMS-App",
    r"C:\Users\hrrah\git\repository\OMS-App",
    r"D:\Appium\Code\OMS-App"
]

def run_git(cmd, cwd):
    res = subprocess.run(cmd, cwd=cwd, shell=True, capture_output=True, text=True)
    return res.returncode, res.stdout.strip(), res.stderr.strip()

def sync_all(commit_msg=None):
    print("=" * 60)
    print("      OMS-APP DUAL-LOCATION & GIT REPOSITORY SYNC     ")
    print("=" * 60)

    # 1. Check which clone has changes and commit/push
    for c in clones:
        if not os.path.exists(c):
            continue
        code, out, err = run_git("git status --porcelain", c)
        if out:
            print(f"\n[DETECTED CHANGES] Found local changes in {c}:")
            print(out)
            msg = commit_msg or "auto: sync changes across OMS-App locations"
            run_git("git add -A", c)
            code, out, err = run_git(f'git commit -m "{msg}"', c)
            print(f"[COMMITTED] in {c}: {out}")
            code, out, err = run_git("git push origin main", c)
            if code == 0:
                print(f"[PUSHED SUCCESS] {c} -> origin/main")
            else:
                print(f"[PUSH ERROR] in {c}: {err}")

    # 2. Pull origin/main into all clones
    print("\n[UPDATING ALL LOCATIONS]")
    for c in clones:
        if not os.path.exists(c):
            continue
        code, out, err = run_git("git pull origin main", c)
        print(f"  * {c} -> {out or err}")

    print("\n" + "=" * 60)
    print(" [SYNC COMPLETE] All OMS-App locations are fully up to date.")
    print("=" * 60)

if __name__ == "__main__":
    msg = sys.argv[1] if len(sys.argv) > 1 else None
    sync_all(msg)
