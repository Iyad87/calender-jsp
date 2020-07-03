var calendar = null;

function editEvent(data) {
    var idString = data.id||"";
    $("#delete-event").toggle(idString != "");
    $("#id").val(data.id)
    $("#title").val(data.title)
    $("#datepicker1").val($.fullCalendar.moment(data.start).format("DD-MM-YYYY"))
    $("#timepicker1").val($.fullCalendar.moment(data.start).format("HH:mm"))
    $("#datepicker2").val($.fullCalendar.moment(data.end).format("DD-MM-YYYY"))
    $("#timepicker2").val($.fullCalendar.moment(data.end).format("HH:mm"))
    $("#public").prop('checked', data.public == 1)
    $("#option").prop('checked', data.option == 1)
    $("#remark").val(data.remark)
    $("#dialog").dialog("open");
}

$(document).ready(function() {

    $("#create_event").click(function(e) {
        $("#dialog").dialog("open");
    });

    $(".datepicker").datepicker();
    
    $(".datepicker").datepicker("option", "dateFormat", "dd-mm-yy");

    $(".timepicker").timepicker({
        hours: {
            starts: 0,
            ends: 23
        },
        minutes: {
            interval: 15
        },
        rows: 4,
        showPeriodLabels: true,
        minuteText: "Min"
    });
    
    $("#dialog").dialog({
        autoOpen: false,
        modal: true,
        width: 800,
        buttons: [{
            text: "OK",
            click: function() {
                var res = {
                    "title": $("#title").val(),
                    "start": $.fullCalendar.moment($("#datepicker1").val(), "DD-MM-YYYY").format("YYYY-MM-DD") +
                        " " + $("#timepicker1").val(),
                    "end": $.fullCalendar.moment($("#datepicker2").val(), "DD-MM-YYYY").format("YYYY-MM-DD") +
                        " " + $("#timepicker2").val(),
                    "public": $("#public").is(':checked') ? 1 : 0,
                    "option": $("#option").is(':checked') ? 1 : 0,
                    "type": $("#type").val(),
                    "remark": $("#remark").val()
                }
                if ($("#id").val() != "") {
                    res.id = $("#id").val();
                }
                $.ajax({
                    url: "service/save-event",
                    data: res,
                    type: "POST",
                    success: function(json) {
                        calendar.fullCalendar("refetchEvents");
                    }
                });
                $(this).dialog("close");
            },
        }, {
            text: "Annuleer",
            click: function() {
                $(this).dialog("close");
            },
        }, {
            text: "Delete",
            id: "delete-event",
            click: function() {
                var res = {};
                if ($("#id").val() != "") {
                    res.id = $("#id").val();
                    calendar.fullCalendar('removeEvents', res.id);
                    $.ajax({
                        url: "service/delete-event",
                        data: res,
                        type: "POST"
                    });
                }
                $(this).dialog("close");
            },
        }]
    });
    
    calendar = $("#calendar").fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        defaultDate: "2018-06-12",
        editable: true,
        eventLimit: true,
        events: "service/event-list",
        selectable: true,
        selectHelper: true,
        select: function(start, end, allDay) {
            editEvent({
                start: start,
                end: start,
            });
            calendar.fullCalendar("unselect");
        },
        eventClick: function(calEvent, jsEvent, view) {
            $.ajax({
                url: "service/get-event?id=" + calEvent.id,
                type: "GET",
                dataType: "json",
                success: function(json) {
                    editEvent(json);
                }
            });
            calendar.fullCalendar("unselect");
        },
        editable: true,
        eventDrop: function(event, delta) {
            start = $.fullCalendar.moment(event.start).format();
            end = $.fullCalendar.moment(event.end).format();
            $.ajax({
                url: "service/update-event",
                data: "start=" + start + "&end=" + end + "&id=" + event.id,
                type: "POST",
                success: function(json) {
                }
            });
        },
        eventResize: function(event) {
            start = $.fullCalendar.moment(event.start).format();
            end = $.fullCalendar.moment(event.end).format();
            $.ajax({
                url: "service/update-event",
                data: "start=" + start + "&end=" + end + "&id=" + event.id,
                type: "POST",
                success: function(json) {
                }
            });
        }
    });

});
