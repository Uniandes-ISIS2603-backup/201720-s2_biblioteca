(function (ng) {
    var mod = ng.module("blogsModule");
    mod.constant("blogsContext", "api/999/blog");
    mod.controller('blogsCtrl', ['$scope', '$http', 'blogsContext', '$state',
        function ($scope, $http, blogsContext, $state) {
            $http.get(blogsContext).then(function (response) {
                $scope.blogRecords = response.data;
            });
            
            if ($state.params.blogId !== undefined && ($state.params.blogId !== null)) {
                $http.get(blogsContext + '/' + $state.params.blogId).then(function (response) {
                      $scope.blogRecords = response.data.blogs;
                  $scope.currentBlog = response.data;
                });
            }
        }
    ]);
}
)(angular);