import cmdmap


@cmdmap.cmd_init("GET_SITE")
def get_siteid(params):
    site = params["site"]
    return site