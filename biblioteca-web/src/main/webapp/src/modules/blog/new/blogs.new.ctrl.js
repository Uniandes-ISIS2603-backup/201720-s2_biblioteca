(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "api/999/blogs");
    mod.controller('blogNewCtrl', ['$scope', '$http', 'blogsContext', '$state', '$rootScope',
        function ($scope, $http, blogsContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createBlog = function () {
                $http.post(blogsContext, {
                    name: $scope.blogName,
                    calificacion: $scope.blogCalificacion,
                
                }).then(function (response) {
                    //Blog created successfully
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(windows.angular);
