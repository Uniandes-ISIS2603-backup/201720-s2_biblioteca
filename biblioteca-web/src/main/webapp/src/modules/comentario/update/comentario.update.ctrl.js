(
        function (ng) {
            var mod = ng.module("comentariosModule");
            mod.constant("comentariosContext", "api/999/comentarios");
            mod.controller('comentariosUpdateCtrl', ['$scope', '$http', 'comentariosContext', '$state', 'comentariosContext', '$rootScope',
                function ($scope, $http, comentariosContext, $state, comentariosContext, $rootScope) {
                    $rootScope.edit = true;

                    var idComentario = $state.params.comentarioId;

                    //Consulto el autor a editar.
                       console.info(comentariosContext+'/' + idComentario);
                    $http.get(comentariosContext + '/' + idComentario).then(function (response) {
                        var comentario = response.data;
                        $scope.comentarioName = comentario.name;
                        $scope.comentarioCalificacion = comentario.calificacion;
                        $scope.comentario = comentario.comentario;
                    });           
                    

                    $scope.createComentario = function () {                   
                        $http.put(comentariosContext + '/' + idComentario, {
                          name: $scope.comentarioName,
                   calificacion: $scope.comentarioCalificacion,
                   comentario: $scope.comentario,
                        }).then(function (response) {                            
                            $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);