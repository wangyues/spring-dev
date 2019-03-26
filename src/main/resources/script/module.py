import os


def visit_import(arg, dir_name, filenames):
    for filename in filenames:
        if filename == '__init__.py' or filename[-3:] != '.py':
            continue
        import_name = dir_name.replace("/", ".") + "." + filename[:-3]
        __import__(import_name, locals(), globals())


def auto_import(dir_name):
    os.path.walk(dir_name, visit_import, None)