fibonacci() {
    pedro=$1
    if [ $pedro -eq 0 ]; then
        echo 0
        exit 0
    fi
    if [ $pedro -eq 1 ]; then
        echo 1
        exit 0
    fi
    a=$(fibonacci $(($pedro - 1)))
    b=$(fibonacci $(($pedro - 2)))
    echo $(($a + $b))
}

fibonacci $1
