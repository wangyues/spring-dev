import json
import cmdmap
import httplib
import urllib2
import tornado


#@cmdmap.cmd_init("GET_SITE")
def get_siteid(params):
    url = params["url"] + "/wbxmbs2/siteservice/{version}/sites/thissite"
    headers = {
        "AppName": "APP_ADDIN",
        "AppToken": params['token']
    }

    request = tornado.httpclient.HTTPRequest(
        url,
        "GET",
        headers=headers)

    client = tornado.httpclient.HTTPClient()
    response = client.fetch(request)

    return response.body

