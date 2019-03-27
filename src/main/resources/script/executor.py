import os
import sys
import json
import traceback
import cmdmap
import module


current = os.path.split(os.path.realpath(__file__))[0]
os.chdir(current)
module.auto_import("cmds")

def main():
    argv = sys.argv  
    params = json.loads(argv[1])
    cmd = params.get("cmd")
    func = cmdmap.maps[str(cmd)]
    return func(params)

if __name__ == "__main__":
    try:
        print main()
    except:
        print traceback.format_exc()