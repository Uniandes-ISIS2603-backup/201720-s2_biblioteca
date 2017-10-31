(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "api/999/blogs");
    mod.controller('blogsCtrl', ['$scope', '$http', 'blogsContext',
        function ($scope, $http, blogsContext,$state) {
            $http.get('data/blogs.json').then(function (response) {
                $scope.blogsRecords = response.data;
          });
            
            if ($state.params.blogId !== undefined) {
                $http.get(blogContext + '/' + $state.params.blogId).then(function (response) {
                    $scope.blogsRecords = response.data.blog;
                    $scope.currentBlog = response.data;
                });
            }
        }
    ]);
}
)(windows.angular);