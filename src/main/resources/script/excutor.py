import sys
import json
import traceback
import cmdmap
import module

module.auto_import("cmds")

def main():
    argv = sys.argv
    print argv[1]
    params = json.loads(argv[1])
    cmd = params.get("cmd")
    func = cmdmap.maps[cmd]
    return func(params)

if __name__ == "__main__":
    try:
        print main()
    except:
        print traceback.format_exc()