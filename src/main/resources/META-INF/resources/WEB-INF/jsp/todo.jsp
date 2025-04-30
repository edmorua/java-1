<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Enter Todo Details</h1>
	<form:form method="post" modelAttribute="todo" >
		<fieldset class="mb-3">
			<form:label path="description">Description</form:label>
			<form:input path="description" type="text" name="description" required="required"/>
			<form:errors path="description" cssClass="text-warning" />
		</fieldset>

		<fieldset class="mb-3">
			<form:label path="targetDate">Target Date</form:label>
			<form:input path="targetDate" type="text" name="targetDate" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>


		<form:input path="id" type="hidden" name="id"/>
		<form:input path="done" type="hidden" name="done"/>
		<input class="btn btn-success" type="submit" value="Submit" />

	</form:form>
</div>
<%@ include file="common/footer.jspf"%>
<script type="text/javascript">
		$('#targetDate').datepicker({
			format: 'yyyy-mm-dd'
		});
</script>
