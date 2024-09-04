USER_PATH=auth
URI=localhost:8080

user_input=$1
api_test(){
    case $1 in
        "post-user")
            curl "${URI}/${USER_PATH}/register" -X POST -H "Content-Type: application/json" \
                -d \
                "{
                    \"email\":\"rojas.elias${RANDOM}@outlook.com\",
                    \"password\":\"EliasRojas874\",
                    \"username\":\"joji${RANDOM}\",
                    \"firstName\":\"Elias\",
                    \"lastName\":\"Rojas\"
                }"

            ;;
        "post-user_fail")
            curl "${URI}/${USER_PATH}/register" -X POST -H "Content-Type: application/json" \
                -d \
                '{
                  "email":"rojas.eliasoutlook.com",
                    "password":"lakjsdf",
                    "username":"dooo"
                }'
            ;;
        "post-user_failpassword")
            curl "${URI}/${USER_PATH}/register" -X POST -H "Content-Type: application/json" \
                -d \
                '{
                  "email":"rojas.eliasoutlook.com",
                    "password":"lakjsdf",
                    "username":"dooo"
                }'
            ;;
        "post_and_get")
            curl "${URI}/${USER_PATH}/register" -X POST -H "Content-Type: application/json" \
                -d \
                '{
                  "email":"ew@outlook.com",
                    "password":"IsInMyDreams234",
                    "username":"onlytimewespeak234",
                    "firstName": "Elias",
                    "lastName": "Rojas"
                }';
            ;;
       "get-user")
            curl "${URI}/${USER_PATH}/$2"
            ;;
        "test-jwt")
            token=$(api_test "post-user" | jq '.token' | tr -d '"')
            echo "Token received ${token}"
            curl -H "Authorization: Bearer ${token}" "${URI}/${USER_PATH}/authenticated"
            ;;
    esac
}

api_test $1
