import cmdmap
import json

@cmdmap.cmd_init("GET_SITE")
def get_siteid(params):
    return json.dumps(params)