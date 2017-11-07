(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogContext", "api/999/blogs");
    mod.controller('blogCtrl', ['$scope', '$http', 'blogContext', '$state',
        function ($scope, $http, blogContext,$state) {
            $http.get(blogContext).then(function (response) {
                $scope.blogRecords = response.data;
          });
            
            if ($state.params.blogId !== undefined) {
                $http.get(blogContext + '/' + $state.params.blogId).then(function (response) {
                    $scope.blogRecords = response.data.blog;
                    $scope.currentBlog = response.data;
                });
            }
        }
    ]);
}
)(windows.angular);