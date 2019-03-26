
maps = {}


def cmd_init(cmd):
    def wrapper(func):
        maps[cmd] = func

        def _func():
            return func()
        return _func
    return wrapper