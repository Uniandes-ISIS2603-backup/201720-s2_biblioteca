(function (ng) {
    var mod = ng.module("eBookModule");
    mod.constant("eBooksContext", "api/999/eBooks");
    mod.controller('eComentarioDeleteCtrl', ['$scope', '$http', 'eBooksContext', '$state',
        function ($scope, $http, eBooksContext, $state) {
            var idEBook = $state.params.eBookId;
            var idEComentario = $state.params.eComentarioId;
            $scope.eb = idEBook;
            $scope.ec = idEComentario;

            console.log({"eb":idEBook,
            "ec":idEComentario});
            $scope.deleteEComentario = function () {
                console.log("entro a la funcion");
                $http.delete(eBooksContext + '/' + idEBook + '/comments/' + idEComentario, {}).then(function (response) {
                    $state.go('eBookDetail', {eBookId: idEBook}, {reload: true});
                });
            };
        }
    ]);
})(angular);