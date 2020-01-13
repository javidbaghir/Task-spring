package task.repository.impl;

public class SqlQuery {

    public static final String GET_PERSON_LIST_WITH_PAGING = "select id, task_name, description, " +
            "  start_date, end_date, user_name   " +
            "from task " +
            "where concat(id, task_name, description, " +
            "    date_format(start_date, '%d.%m.%Y'), " +
            "    date_format(end_date, '%d.%m.%Y'), user_name) like ? " +
            " order by {SORT_COLUMN} {SORT_DIRECTION} " +
            " limit ?, ?";

    public static final String GET_TASK_COUNT = "select count(id) as task_count from task";

    public static final String GET_FILTERED_PERSON_COUNT = "select count(*) as task_count  " +
            "        from task " +
            "        where concat(id, task_name, description, " +
            "            date_format(start_date, '%d.%m.%Y'), " +
            "            date_format(end_date, '%d.%m.%Y'), user_name ) like ?";

    public static final String INSERT_ADD_TASK = "insert into task(id, task_name, description, start_date, end_date, user_name) " +
            "values(null, :task_name, :description, :start_date, :end_date, :user_name) ";

    public static final String DELETE_TASK = "delete from task " +
            "where id = :id ";

    public static final String SELECT_TASK = "select id, task_name, description, start_date , end_date, user_name " +
            "from task " +
            " where id = :id ";

    public static final String UPDATE_TASK = "  update task set task_name = :task_name, description = :description, " +
            "start_date = :start_date, end_date = :end_date, user_name = :user_name " +
            "where id = :id ";

}
