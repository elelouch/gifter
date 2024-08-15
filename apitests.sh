USER_PATH=user

user_input=$1
case $user_input in
    "post-user")
        curl "localhost:8080/${USER_PATH}" -X POST -H "Content-Type: application/json" \
            -d \
            '{
              "email":"rojas.elias@outlook.com",
                "password":"EliasRojas874",
                "username":"dooo"
            }'
        ;;
    "get")
        echo "holis"
        ;;
esac
