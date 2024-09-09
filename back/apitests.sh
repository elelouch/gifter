URI=localhost:8080

user_input=$1
api_test(){
    case $1 in
        "login-admin") 
            login_admin
            ;;
        "post-user")
            curl -H "Content-Type: application/json" \
                -d \
                "{
                    \"email\":\"rojas.elias${RANDOM}@outlook.com\",
                    \"password\":\"EliasRojas874\",
                    \"username\":\"joji${RANDOM}\",
                    \"firstName\":\"Elias\",
                    \"lastName\":\"Rojas\"
                }" "${URI}/auth/register" 

            ;;
    esac
}

login_admin() {
        tokenReq=$(curl "${URI}/auth/login" -X POST -H "Content-Type: application/json" \
            -d \
            "{
                \"email\":\"admin@gifter.com\",
                \"password\":\"admin123\"
            }")
        jq $tokenReq ".id" | tr '"'
}

create_user() {
    token=$(api_test "post-user" | jq '.token' | tr -d '"')
    user_id=$(curl -H "Authorization: Bearer $token" "${URI}/user/current" | jq '.id' | tr -d '"')
    echo "$token $user_id"
}

api_test_authenticated (){
    read -ra user_data <<< $(create_user)
    action=$1
    token=${user_data[0]}
    id=${user_data[1]}
    echo "tokenardo : $token"
    echo "id : $id"
    api_auth $token $id $action
}

api_auth() {
    token=$1
    user_id=$2
    action=$3
    case $action in
        "add-gifts")
            curl -v -H "Authorization: Bearer $token"  \
                -H "Content-Type: application/json" \
                -d "{\"list\":
                            [
                                { 
                                    \"id\":\"0\",
                                    \"name\":\"pedro\",
                                    \"location\":\"somewhere\",
                                    \"imageUrl\":\"paparulo uwu\"
                                }

                            ]
                        }" "${URI}/gift"
            ;;
        "get-gifts")
            api_auth $token $user_id "add-gifts"
            curl -v -H "Authorization: Bearer $token" "${URI}/gift"
            ;;
    esac
}

if [ "$1" = "auth" ]; then
    api_test_authenticated $2
else 
    api_test $1
fi
