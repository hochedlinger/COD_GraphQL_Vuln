import json
import requests
from urllib import parse

paths = [
    "",
    "graphql",
    "graphql/v1",
    "graphql/api",
    "graphql/admin",
    "graphql/console",
    "graphql/graphql",
    "graphql.php",
    "graphiql",
    "api/graphql",
    "admin",
    "explorer",
    "altair",
    "playground",
    "api",
]

query = {
    "query": "{__typename}"
}

for path in paths:
    hostname = 'https://0a9200ed032d62c781c0890600ec0005.web-security-academy.net/'
    endpoint = parse.urljoin(hostname, path)
    print(f"Attempt: {endpoint}")
    ## GET
    try:
        get_response = requests.get(endpoint, json=query, timeout=1)

        if get_response.status_code == 200:
            json_data = json.loads(get_response.text)
            if json_data.get('data'):
                print("[SUCCESS] GraphQL endpoint via GET request: ",endpoint)
    except Exception:
        pass

    ## POST
    try:
        post_response = requests.post(endpoint, json=query, timeout=1)

        if post_response.status_code == 200:
            json_data = json.loads(post_response.text)
            if json_data.get('data'):
                print("[SUCCESS] GraphQL endpoint via POST request: ",endpoint)
    except Exception:
        pass