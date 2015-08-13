def tasks = [:]
tasks["sanity-test"] = {
      node('node2') {
              stage concurrency: 2, name: 'sanity-test'
                echo "INFO - Ending sanity-tests"
      }
}
tasks["functional-test"] = {
      node('node2') {
              stage concurrency: 3, name: 'functional-test'
              echo "INFO - Ending functional-tests"
      }
}

parallel tasks
