(function (ng) {
    var mod = ng.module("blogModule");
    mod.controller('blogDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idBlog = $state.params.blogId;
            $scope.deleteBlog = function () {
                 $http.delete('api/999/blogs' + '/' + idBlog, {}).then(function (response) {
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(windows.angular);