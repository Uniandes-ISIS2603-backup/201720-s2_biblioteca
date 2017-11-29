(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosBlogCrearContext", "api/999/comentarios/blogCrear");
    mod.controller('comentarioBlogNewCtrl', ['$scope', '$http', 'comentariosBlogCrearContext', '$state', '$rootScope',
        function ($scope, $http, comentariosBlogCrearContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createComentario = function () {
                $http.post(comentariosBlogCrearContext + "/"+ $state.params.blogId, {
                    name: $scope.comentarioName,
                   calificacion: $scope.comentarioCalificacion,
                   comentarioC:$scope.comentarioComentarioC,
               
                }).then(function (response) {
                    //comentario created successfully
                    $state.go('comentariosBlogList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
