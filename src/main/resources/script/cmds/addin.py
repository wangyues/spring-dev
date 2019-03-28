import json
import cmdmap
import httplib
import urllib2
import tornado.httpclient

@cmdmap.cmd_init("GET_SITE")
def get_siteid(params):
    url = params["site"] + "/wbxmbs2/siteservice/v1/sites/thissite"
    headers = {
        "AppName": "APP_ADDIN",
        "AppToken": params['token']
    }
    import os
    request = tornado.httpclient.HTTPRequest(
        url,
        "GET",
        headers=headers)
    client = tornado.httpclient.HTTPClient()
    response = client.fetch(request)

    return response.body