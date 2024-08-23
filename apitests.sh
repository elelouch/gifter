USER_PATH=user
URI=localhost:8080

user_input=$1
case $user_input in
    "post-user")
        curl "${URI}/${USER_PATH}/register" -X POST -H "Content-Type: application/json" \
            -d \
            '{
              "email":"rojas.elias@outlook.com",
                "password":"EliasRojas874",
                "username":"dooo"
            }'
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
    "get-user")
        curl "${URI}/${USER_PATH}/$2"
        ;;
esac
