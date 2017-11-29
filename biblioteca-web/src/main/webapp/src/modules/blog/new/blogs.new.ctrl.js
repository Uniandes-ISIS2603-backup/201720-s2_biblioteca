(function (ng) {
    var mod = ng.module("blogsModule");
    mod.constant("blogsContext", "api/999/blog");
    mod.controller('blogNewCtrl', ['$scope', '$http', 'blogsContext', '$state', '$rootScope',
        function ($scope, $http, blogsContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createBlog = function () {
                $http.post(blogsContext, {
                    name: $scope.blogName,
                   calificacion: $scope.blogCalificacion,
                   blogC: $scope.blogC,
                }).then(function (response) {
                    //blog created successfully
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
